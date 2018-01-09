package it.nowicki.jaroslaw;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

public class MaxTemperatureMapperTest {

    private static final String recordExample1 = "0043011990999991950051518004+68750+023550FM-12+0382" +
            "99999V0203201N00261220001CN9999999N9+99991+99999999999";

    private static final String recordExample2 = "0043011990999991950051518004+68750+023550FM-12+0382" +
            "99999V0203201N00261220001CN9999999N9-00111+99999999999";

    @Test
    public void ignore() throws IOException {
        Text value = new Text(recordExample1);
        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new MaxTemperatureMapper())
                .withInput(new LongWritable(0), value)
                .runTest();
    }


    @Test
    public void parsesValidRecord() throws IOException, InterruptedException {
        Text value = new Text(recordExample2);
        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new MaxTemperatureMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new Text("1950"), new IntWritable(-11))
                .runTest();
    }



}
