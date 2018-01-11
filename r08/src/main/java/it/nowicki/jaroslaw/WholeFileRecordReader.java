package it.nowicki.jaroslaw;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.FileSplit;

import org.apache.hadoop.mapreduce.RecordReader;

import java.io.IOException;

public class WholeFileRecordReader extends RecordReader<NullWritable, BytesWritable> {

    private FileSplit fileSplit;
    private Configuration conf;
    private BytesWritable value = new BytesWritable();
    private boolean processed = false;



    public void initialize(org.apache.hadoop.mapreduce.InputSplit inputSplit, org.apache.hadoop.mapreduce.TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        this.fileSplit = (FileSplit) inputSplit;
        this.conf = taskAttemptContext.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (!processed) {
            byte[] contents = new byte[(int) fileSplit.getLength()];
            Path file = fileSplit.getPath();
            FileSystem fileSystem = file.getFileSystem(conf);
            FSDataInputStream in = null;
            try {
                in = fileSystem.open(file);
                IOUtils.readFully(in, contents, 0, contents.length);
                value.set(contents, 0, contents.length);
            } finally {
                IOUtils.closeStream(in);
            }
            processed = true;
            return true;

        }

        return false;
    }

    public NullWritable getCurrentKey() throws IOException, InterruptedException  {
        return NullWritable.get();
    }

    public BytesWritable getCurrentValue() throws IOException, InterruptedException  {
        return value;
    }

    public float getProgress() throws IOException, InterruptedException  {
        return processed ? 1.0f : 0.0f;
    }

    public void close() throws IOException  {

    }


}
