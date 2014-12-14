package decode.p25.message.pdu.osp.voice;

import alias.AliasList;
import bits.BitSetBuffer;
import decode.p25.message.pdu.UnitToUnitChannelGrantExtended;
import decode.p25.message.tsbk.osp.control.IdentifierUpdateReceiver;
import decode.p25.reference.DataUnitID;
import decode.p25.reference.Opcode;

public class UnitToUnitVoiceChannelGrantExtended 
				extends UnitToUnitChannelGrantExtended 
				implements IdentifierUpdateReceiver
{
	public UnitToUnitVoiceChannelGrantExtended( BitSetBuffer message,
            DataUnitID duid, AliasList aliasList )
    {
	    super( message, duid, aliasList );
    }

    @Override
    public String getEventType()
    {
        return Opcode.UNIT_TO_UNIT_VOICE_CHANNEL_GRANT.getDescription();
    }
}