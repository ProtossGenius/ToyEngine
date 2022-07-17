package com.suremoon.gametest.test_net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleNettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * ���������ڶ�ȡ�ͻ��˷��͵���Ϣ
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("SimpleNettyServerHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        byte[] bytesMsg = new byte[result.readableBytes()];
        // msg�д洢����ByteBuf���͵����ݣ������ݶ�ȡ��byte[]��
        result.readBytes(bytesMsg);
        String resultStr = new String(bytesMsg);
        // ���ղ���ӡ�ͻ��˵���Ϣ
        System.out.println("Client said:" + resultStr);
        // �ͷ���Դ�����кܹؼ�
        result.release();

        // ��ͻ��˷�����Ϣ
        String response = "hello client!";
        // �ڵ�ǰ�����£����͵����ݱ���ת����ByteBuf����
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }

    /**
     * ���������������쳣
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // �������쳣�͹ر�����
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * ��Ϣ��ȡ��Ϻ����
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

}