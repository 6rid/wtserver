/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtserver.server;

import java.nio.ByteBuffer;
import java.util.Random;
import wtserver.client.ServerMsg;

/**
 *
 * @author MSI
 */
public class ExitAck extends ServerMsg {
    private final short msgId = ServerMsg.CS_FD_EXIT_ACK;
    byte slot;
    
    public ExitAck(byte slot)
    {
        size = 24;
        buffer = ByteBuffer.allocate(size);
        this.slot = slot;
    }
    
    public byte [] getData(short seqNum)
    {
        buffer.position(0);
        byte random = (byte) new Random().nextInt();
        addByte(random);
        addShort(msgId);
        addShort(seqNum);
        short pSize = (short) ((size - 8) / 16);
        addShort(pSize);
        byte checksum = 0;
        for(int i = 0; i < 7; i++)
        {
            checksum += buffer.get(i);
        }
        addByte(checksum);
        addByte((byte)0);
        addByte((byte)slot);
        for(int i = buffer.position(); i < buffer.capacity(); i++)
        {
            addByte((byte)0);
        }
        return buffer.array();
    }
}
