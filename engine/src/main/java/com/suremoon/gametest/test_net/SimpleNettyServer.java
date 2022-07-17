package com.suremoon.gametest.test_net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SimpleNettyServer {
    public void bind(int port) throws Exception {

        // ��������Ӧ�ó���ʹ������NioEventLoopGroup��������EventLoop���飬EventLoop����൱��һ�������̣߳���Netty��������ʹ���IO������̡߳�
        // ���߳���, ���ڽ��ܿͻ��˵����ӣ����ǲ����κδ������ϰ�һ����������
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // ���߳���, ��boss�������Ӳ�ע�ᱻ���ܵ����ӵ�workerʱ�������������ӵ�������
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // netty������������Ĵ���, ���������࣬���ڷ�����ͨ����һϵ������
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)           //�������߳���
                    // ���ڹ���socketchannel����
                    .channel(NioServerSocketChannel.class)   //ָ��NIO��ģʽ
                    .childHandler(new ChannelInitializer<SocketChannel>() {  // �Ӵ����������ڴ���workerGroup
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new NettyServerOutBoundHandler());
                            socketChannel.pipeline().addLast(new SimpleNettyServerHandler());

                        }
                    });

            // ����server���󶨶˿ڣ���ʼ���ս��������ӣ�����8088Ϊ�����Ķ˿ںţ�ͬʱ������ʽΪͬ��
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            System.out.println("server start");
            // �����رյ�channel���ȴ������� socket �ر� ������λͬ����ʽ
            channelFuture.channel().closeFuture().sync();
        } finally {
            //�˳��߳���
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new SimpleNettyServer().bind(port);
    }
}
