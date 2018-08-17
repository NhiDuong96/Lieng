package handler.event;


import bitzero.server.controllers.system.cmd.ReceivePaymentUpdate;
import bitzero.server.core.BZEventParam;
import bitzero.server.core.IBZEvent;
import bitzero.server.exceptions.BZException;
import bitzero.server.extensions.BaseServerEventHandler;

public class PaymentUpdateEventHandler extends BaseServerEventHandler {
    public PaymentUpdateEventHandler() {
        super();
    }

    @Override
    public void handleServerEvent(IBZEvent ibzevent) throws BZException {
        ReceivePaymentUpdate paymentUpdate = (ReceivePaymentUpdate) ibzevent.getParameter(BZEventParam.PAYMENT_UPDATE_DATA);

        Integer providerID = paymentUpdate.providerID;
        Integer userID = paymentUpdate.playerId;
        Integer gross_revenue = paymentUpdate.grossRevenue;
        Integer net_revenue = paymentUpdate.netRevenue;
        Integer coinCash = paymentUpdate.coinCash;
        Integer coinPromo = paymentUpdate.coinPromo;
        Integer paymentItemType = paymentUpdate.paymentItem;
        String paymentMethod = paymentUpdate.paymentMethod;
        String paymentChannel = paymentUpdate.paymentChannel;
        String transactionID = paymentUpdate.transactionID;
    }
}
