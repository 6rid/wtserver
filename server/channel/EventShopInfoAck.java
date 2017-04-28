/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtserver.server.channel;

import java.nio.ByteBuffer;
import java.util.Random;
import wtserver.client.ServerMsg;

/**
 *
 * @author MSI
 */
public class EventShopInfoAck extends ServerMsg {
    private final short msgId = ServerMsg.CS_CH_EVENTSHOPINFO_ACK;
    
    public EventShopInfoAck()
    {
        size = 24;
        buffer = ByteBuffer.allocate(256);
    }
    
    public byte [] getData(short seqNum)
    {
        buffer.position(0);
        byte random = (byte) new Random().nextInt();
        addByte(random);
        addShort(msgId);
        addShort(seqNum);
        addShort((short) 0);
        byte checksum = 0;
        addByte(checksum);
        
        byte b[] = {0x00, 0x03, 0x00, 0x0E, 0x32, 0x30, 0x31, 0x33, 0x30, 0x38, 0x32, 0x30, 0x30, 0x39, 0x33, 0x30, 0x30, 0x30, 0x0E, 0x32, 0x30, 0x31, 0x33, 0x30, 0x38, 0x32, 0x33, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x00, 0x00, 0x01, 0x0E, 0x32, 0x30, 0x31, 0x32, 0x31, 0x31, 0x30, 0x35, 0x32, 0x32, 0x33, 0x30, 0x30, 0x30, 0x0E, 0x32, 0x30, 0x31, 0x32, 0x31, 0x32, 0x33, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x00, 0x00, 0x02, 0x0E, 0x32, 0x30, 0x31, 0x32, 0x30, 0x31, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x0E, 0x32, 0x30, 0x31, 0x32, 0x30, 0x31, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x00, 0x00, 0x03, 0x00, 0x01, 0x3D, 0x01, 0x00, 0x3D, 0x02, 0x01, 0x3D, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        
        for(int i = 0; i < b.length; i++)
        {
            addByte(b[i]);
        }
        
        size = (short) buffer.position();
        if((size - 8) % 16 > 0)
        {
            size -= (size - 8) % 16;
            size += 16;
        }
        short pSize = (short) ((size - 8) / 16);
        buffer.position(5);
        addShort(pSize);
        for(int i = 0; i < 7; i++)
        {
            checksum += buffer.get(i);
        }
        addByte(checksum);
        return buffer.array();
    }
}