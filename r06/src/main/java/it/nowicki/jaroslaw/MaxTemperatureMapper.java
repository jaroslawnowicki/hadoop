package it.nowicki.jaroslaw;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;


public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private NCDCRecordParser parser = new NCDCRecordParser();

    enum Temperature {
        MALFORMED
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        parser.parse(value);

        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        System.out.println(fileSplit.getPath());  //info about file

        if (parser.isValidTemperature()) {
            context.write(new Text(parser.getYear()), new IntWritable(parser.getAirTemperature()));
        } else {
            context.getCounter(Temperature.MALFORMED).increment(1);
        }
    }


}
