package it.nowicki.jaroslaw;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

public class MaxTemperatureDriverTest {

    @Test
    public void test() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        conf.set("mapreduce.framework.nam", "local");
        conf.setInt("mapreduce.taks.io.sort.mb", 1);

        Path input = new Path("input/ncdc/micro");
        Path output = new Path("output");

        FileSystem fs = FileSystem.get(conf);
        fs.delete(output, true);

        MaxTemperatureDriver driver = new MaxTemperatureDriver();

        driver.setConf(conf);
        int exitCode = driver.run(new String[] {
           input.toString(), output.toString()
        });

        System.exit(exitCode);

    }
}
