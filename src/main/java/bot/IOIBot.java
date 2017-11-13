package bot;

import clients.FixClient;
import config.IOIBotConfig;
import model.Action;
import model.IOI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.client.SymphonyClient;
import org.symphonyoss.client.events.*;
import org.symphonyoss.client.exceptions.SymException;
import org.symphonyoss.client.model.Room;
import org.symphonyoss.client.services.RoomEventListener;
import org.symphonyoss.client.services.RoomService;
import org.symphonyoss.client.services.RoomServiceEventListener;
import org.symphonyoss.symphony.clients.model.SymMessage;
import org.symphonyoss.symphony.clients.model.SymUser;
import org.symphonyoss.symphony.pod.model.MemberInfo;
import org.symphonyoss.symphony.pod.model.MembershipList;

import java.util.List;

public class IOIBot implements RoomServiceEventListener, RoomEventListener {

    private static IOIBot instance;
    private final Logger logger = LoggerFactory.getLogger(IOIBot.class);
    private SymphonyClient symClient;
    private RoomService roomService;
    private IOIBotConfig config;
    private FixClient fixClient;


    protected IOIBot(SymphonyClient symClient, IOIBotConfig config) {
        this.symClient=symClient;
        this.config = config;
        this.fixClient = new FixClient();
        init();


    }

    public static IOIBot getInstance(SymphonyClient symClient, IOIBotConfig config){
        if(instance==null){
            instance = new IOIBot(symClient,config);
        }
        return instance;
    }

    private void init() {

        roomService = symClient.getRoomService();
        roomService.addRoomServiceEventListener(this);


    }

    @Override
    public void onRoomMessage(SymMessage message) {
        String senderCompany = message.getSymUser().getCompany();
        String targetCompany = null;
        MembershipList memberList;
        SymUser sender;
        try {
            memberList = symClient.getRoomMembershipClient().getRoomMembership(message.getStreamId());
            sender = symClient.getUsersClient().getUserFromId(message.getSymUser().getId());
            senderCompany=sender.getCompany();
            for (MemberInfo user: memberList ) {
                SymUser roomMember = symClient.getUsersClient().getUserFromId(user.getId());
                if(!roomMember.getCompany().equals(senderCompany)){
                    targetCompany = roomMember.getCompany();
                }
            }
        } catch (SymException e) {
            e.printStackTrace();
        }


        try{
            String[] parsedText = message.getMessageText().split("\\s+");

            IOI ioi = new IOI(parsedText[0].equals("S")? Action.S:Action.B,parsedText[2],parsedText[1],senderCompany,targetCompany);

            this.fixClient.sendFixMessageFromSymphony(ioi);

            SymMessage aMessage = new SymMessage();
            aMessage.setMessageText("IOI was processed");

            symClient.getMessagesClient().sendMessage(message.getStream(), aMessage);


        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    @Override
    public void onNewRoom(Room room) {
        room.addEventListener(this);
    }

    @Override
    public void onMessage(SymMessage symMessage) {

    }

    @Override
    public void onSymRoomDeactivated(SymRoomDeactivated symRoomDeactivated) {

    }

    @Override
    public void onSymRoomMemberDemotedFromOwner(SymRoomMemberDemotedFromOwner symRoomMemberDemotedFromOwner) {

    }

    @Override
    public void onSymRoomMemberPromotedToOwner(SymRoomMemberPromotedToOwner symRoomMemberPromotedToOwner) {

    }

    @Override
    public void onSymRoomReactivated(SymRoomReactivated symRoomReactivated) {

    }

    @Override
    public void onSymRoomUpdated(SymRoomUpdated symRoomUpdated) {

    }

    @Override
    public void onSymUserJoinedRoom(SymUserJoinedRoom symUserJoinedRoom) {

    }

    @Override
    public void onSymUserLeftRoom(SymUserLeftRoom symUserLeftRoom) {

    }

    @Override
    public void onSymRoomCreated(SymRoomCreated symRoomCreated) {

    }

}
