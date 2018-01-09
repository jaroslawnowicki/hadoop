package it.nowicki.jaroslaw;


import org.apache.hadoop.conf.Configuration;

public class Conf {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");

        System.out.println(conf.get("size"));
        System.out.println(conf.get("color"));

        System.setProperty("weight", "20");

        System.out.println(conf.get("weight"));


    }
}
