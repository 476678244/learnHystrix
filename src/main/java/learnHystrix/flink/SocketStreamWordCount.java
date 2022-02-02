package learnHystrix.flink;


import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * flink入门案例
 * Socket发送文本，flink对文本进行单词统计
 *
 * @Author: rugu
 * @Date: 2021/12/27 10:50
 */
public class SocketStreamWordCount {
    public static void main(String[] args) throws Exception {

        final int ARGS_LENGTH = 2;

        // Socket参数校验
        if (args.length < ARGS_LENGTH) {
            System.err.println("ERROR：参数校验失败，请输入正确的参数 <hostname> <port>");
            return;
        }

        String hostname = args[0];
        String port = args[1];

        // 创建Flink运行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 添加数据源
        DataStream<String> socketTextStream = env.socketTextStream(hostname, Integer.parseInt(port));

        // 对数据分组统计
        DataStream<Tuple2<String, Integer>> sum = socketTextStream.flatMap(new SocketStreamFlatMapFunction())
                .keyBy(0)
                .sum(1);

        sum.print();
        // 运行程序
        env.execute("SocketStream");

    }

    /**
     * 自定义FlatMapFunction
     * 对文本进行切割，并组装成tuple
     */
    private static class SocketStreamFlatMapFunction implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] words = s.split(" ");
            for (String word : words) {
                collector.collect(new Tuple2<String, Integer>(word, 1));
            }
        }
    }
}