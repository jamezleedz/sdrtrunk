package io.github.dsheirer.module.decode.p25.message.ldu.lc;

import io.github.dsheirer.module.decode.p25.message.ldu.LDU1Message;
import io.github.dsheirer.module.decode.p25.reference.ExtendedFunction;
import io.github.dsheirer.module.decode.p25.reference.LinkControlOpcode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtendedFunctionCommand extends LDU1Message
{
	private final static Logger mLog = 
			LoggerFactory.getLogger( ExtendedFunctionCommand.class );
	public static final int[] EXTENDED_FUNCTION = { 364,365,366,367,372,373,374,
		375,376,377,382,383,384,385,386,387 };
	public static final int[] ARGUMENT = { 536,537,538,539,540,541,546,547,548,
		549,550,551,556,557,558,559,560,561,566,567,568,569,570,571 };
	public static final int[] TARGET_ADDRESS = { 720,721,722,723,724,725,730,
		731,732,733,734,735,740,741,742,743,744,745,750,751,752,753,754,755 };

    public ExtendedFunctionCommand( LDU1Message source )
	{
		super( source );
	}
	
    @Override
    public String getEventType()
    {
        return LinkControlOpcode.EXTENDED_FUNCTION_COMMAND.getDescription();
    }

    public String getMessage()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append( getMessageStub() );

        sb.append( " EXTENDED FUNCTION:" + getExtendedFunction().getLabel() );

        sb.append( " ARGUMENT: " + getTargetAddress() );

        sb.append( " TGT ADDR: " + getTargetAddress() );
        
        return sb.toString();
    }
    
    public ExtendedFunction getExtendedFunction()
    {
        return ExtendedFunction.fromValue( mMessage.getInt( EXTENDED_FUNCTION ) );
    }
    
    public String getArgument()
    {
    	return mMessage.getHex( ARGUMENT, 6 );
    }
    
    public String getSourceAddress()
    {
    	switch( getExtendedFunction() )
    	{
			case RADIO_CHECK:
			case RADIO_CHECK_ACK:
			case RADIO_DETACH:
			case RADIO_DETACH_ACK:
			case RADIO_INHIBIT:
			case RADIO_INHIBIT_ACK:
			case RADIO_UNINHIBIT:
			case RADIO_UNINHIBIT_ACK:
				return getArgument();
			default:
				break;
    	}
    	
    	return null;
    }
    
    public String getTargetAddress()
    {
        return mMessage.getHex( TARGET_ADDRESS, 6 );
    }

    @Override
    public String getToID()
    {
        return getTargetAddress();
    }
}
