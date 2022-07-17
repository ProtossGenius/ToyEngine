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

        // 服务器端应用程序使用两个NioEventLoopGroup创建两个EventLoop的组，EventLoop这个相当于一个处理线程，是Netty接收请求和处理IO请求的线程。
        // 主线程组, 用于接受客户端的连接，但是不做任何处理，跟老板一样，不做事
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从线程组, 当boss接受连接并注册被接受的连接到worker时，处理被接受连接的流量。
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // netty服务器启动类的创建, 辅助工具类，用于服务器通道的一系列配置
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)           //绑定两个线程组
                    // 用于构造socketchannel工厂
                    .channel(NioServerSocketChannel.class)   //指定NIO的模式
                    .childHandler(new ChannelInitializer<SocketChannel>() {  // 子处理器，用于处理workerGroup
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new NettyServerOutBoundHandler());
                            socketChannel.pipeline().addLast(new SimpleNettyServerHandler());

                        }
                    });

            // 启动server，绑定端口，开始接收进来的连接，设置8088为启动的端口号，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            System.out.println("server start");
            // 监听关闭的channel，等待服务器 socket 关闭 。设置位同步方式
            channelFuture.channel().closeFuture().sync();
        } finally {
            //退出线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new SimpleNettyServer().bind(port);
    }
}
