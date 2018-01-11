package it.nowicki.jaroslaw;

import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import javax.ws.rs.Path;

//gdy niechcemy dzielić plików z HDFS na porcje
public class NonSplittableTextInputFormat extends TextInputFormat {

    protected boolean isSplitable(JobContext context, Path file) {

        return false;
    }
}
