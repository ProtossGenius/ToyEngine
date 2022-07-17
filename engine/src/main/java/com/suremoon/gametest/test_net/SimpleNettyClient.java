package com.suremoon.gametest.test_net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SimpleNettyClient {

    public void connect(String host, int port) throws Exception {
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            // �ͻ������������
            Bootstrap bootstrap = new Bootstrap();
            /**
             *EventLoop����
             */
            bootstrap.group(worker);
            /**
             * ���ڹ���socketchannel����
             */
            bootstrap.channel(NioSocketChannel.class);
            /**����ѡ��
             * ������Socket�ı�׼������key��value���������аٶ�
             ���ֺ�������Ҫ������
             * */
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            /**
             * �Զ���ͻ���Handle���ͻ�������������飩
             */
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new SimpleNettyClientHandler());
                }
            });

            /** �����ͻ��˼��������ӵ�Զ�̽ڵ㣬�����ȴ�ֱ���������*/
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            /**�����ȴ����ݣ�ֱ��channel�ر�(�ͻ��˹ر�)*/
            channelFuture.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleNettyClient client = new SimpleNettyClient();
        client.connect("127.0.0.1", 8080);

    }

}