package it.nowicki.jaroslaw;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * Created by jarek on 11.01.18.
 */
public class WholeFileInputFormat extends FileInputFormat<NullWritable, BytesWritable> {

    protected boolean isSplitable(JobContext job, Path file) {
        return false;
    }

    public org.apache.hadoop.mapreduce.RecordReader<NullWritable, BytesWritable> createRecordReader(org.apache.hadoop.mapreduce.InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        WholeFileRecordReader reader = new WholeFileRecordReader();
        reader.initialize(inputSplit, taskAttemptContext);
        return reader;
    }
}
