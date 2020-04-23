package org.medibloc.panacea.local;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.medibloc.panacea.*;
import org.medibloc.panacea.domain.*;
import org.medibloc.panacea.domain.did.DIDDocument;
import org.medibloc.panacea.domain.did.PubKey;
import org.medibloc.panacea.domain.did.PubKeyWithId;
import org.medibloc.panacea.domain.did.Service;
import org.medibloc.panacea.domain.message.*;
import org.medibloc.panacea.domain.model.response.Res;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 - name: ykc
 type: local
 address: panacea1uekh7wzhmpjwvjz9lul7uakcr004d6rwskzfdf
 pubkey: panaceapub1addwnpepqtud78npjfwuglnjrl7aa7lteddyaq3gj6av0yt45k3hxspf4p942l53uq3
 mnemonic: ""
 threshold: 0
 pubkeys: []


 **Important** write this mnemonic phrase in a safe place.
 It is the only way to recover your account if you ever forget your password.

 clarify clutch decline mirror inform choose letter switch tuna fine blur unknown air record material emotion dust awake deputy bundle provide build shoulder cart
 */

public class DIDTest {

    private PanaceaApiRestClient restClient = null;

    String hrp = "panacea";

    @Before
    public void setup() {
        restClient = PanaceaApiClientFactory.newInstance()
                .newRestClient(
                        PanaceaEnvironment.LOCAL.getBaseUrl()
                );
    }

    @Test
    public void testMsgCreateDID() throws PanaceaApiException {
        String mnemonic = "clarify clutch decline mirror inform choose letter switch tuna fine blur unknown air record material emotion dust awake deputy bundle provide build shoulder cart";
        Wallet wallet = Wallet.createWalletFromMnemonicCode(mnemonic, "panacea");

        MsgCreateDID msg = new MsgCreateDID();
        MsgCreateDID.Value value = new MsgCreateDID.Value();
        value.setOwnerAddress("panacea1uekh7wzhmpjwvjz9lul7uakcr004d6rwskzfdf");

        PubKey pubkey = new PubKey();
        pubkey.setType("PubKeySecp256k1");
        pubkey.setValue(wallet.getPubKeyForSign().getValue().trim());

        ArrayList<PubKey> pubKeys = new ArrayList<PubKey>();
        pubKeys.add(pubkey);

        value.setPubKeys(pubKeys);

        ArrayList<Service> services = new ArrayList<Service>();
        Service service = new Service();
        service.setId("medi");
        service.setType("resolver");
        service.setServiceEndPoint("endpoint");

        services.add(service);

        value.setServices(services);

        msg.setValue(value);

        TxResponse res = broadcastMsgBlock(msg);

        System.out.println(res);
        String did = res.getLogs().get(0).getLog();

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("did = " + did);
        System.out.println("-----------------------------------------------------------------------");

        Res<DIDDocument> documentRes = restClient.resolveDID(did);
        System.out.println(documentRes);
    }

    @Test
    public void testResolveDID() throws PanaceaApiException {
        Res<DIDDocument> documentRes = restClient.resolveDID("did:med:5DpKj5SRr75YsTyhtEzRmvG9N2epYDHgjNJJD9jcfZnx");
        System.out.println(documentRes);

        PubKeyWithId pubKey = new PubKeyWithId();
        pubKey.setId("did:med:6jM6AzifPVNLDqLxT1deaakzbgYSuaDArpGY1Qc58Z3k#keys-1");
        pubKey.setType("PubKeySecp256k1");
        pubKey.setValue("AvjfHmGSXcR+ch/93vvry1pOgiiWuseRdaWjc0ApqEtV");

        ArrayList<PubKeyWithId> pubkeys = new ArrayList<PubKeyWithId>();
        pubkeys.add(pubKey);

        ArrayList<String> auths = new ArrayList<String>();
        auths.add("did:med:6jM6AzifPVNLDqLxT1deaakzbgYSuaDArpGY1Qc58Z3k#keys-1");

        DIDDocument expected = new DIDDocument();
        expected.setContext("https://w3id.org/did/v1");
        expected.setId("did:med:6jM6AzifPVNLDqLxT1deaakzbgYSuaDArpGY1Qc58Z3k");
        expected.setPublicKey(pubkeys);
        expected.setAuthentication(auths);

        DIDDocument document = documentRes.getResult();

        Assert.assertEquals(expected, document);
    }

    @Test
    public void testResolveDID2() throws PanaceaApiException {
        Res<DIDDocument> documentRes = restClient.resolveDID("did:med:AZWAFY1ie3X4imAfiG4M1PoBqxqQAXpKbSi77fMQeJm");
        System.out.println(documentRes);
    }

    @Test
    public void testMsgAddPublicKey() throws PanaceaApiException {
        String did = "did:med:NHw9RqQR6q74fkPtLubk5tb4tWUaXdznhGNRx2AAFeo";
        String owner = "panacea1uekh7wzhmpjwvjz9lul7uakcr004d6rwskzfdf";

        PubKey pubkey = new PubKey();
        pubkey.setType("PubKeySecp256k1");
        pubkey.setValue("A2CFVQRopFf2FjtWyTxe6ZhDiYgejXcVv1Wx4kzgk32H");

        MsgAddPublicKey.Value value = new MsgAddPublicKey.Value();
        value.setDid(did);
        value.setOwnerAddress(owner);
        value.setPubKey(pubkey);

        MsgAddPublicKey msg = new MsgAddPublicKey();
        msg.setValue(value);

        broadcastMsgBlock(msg);

        Res<DIDDocument> documentRes = restClient.resolveDID(did);
        System.out.println(documentRes);
    }

    @Test
    public void testMsgDeletePublicKey() throws PanaceaApiException {
        String did = "did:med:NHw9RqQR6q74fkPtLubk5tb4tWUaXdznhGNRx2AAFeo";
        String owner = "panacea1uekh7wzhmpjwvjz9lul7uakcr004d6rwskzfdf";

        PubKey pubkey = new PubKey();
        pubkey.setType("PubKeySecp256k1");
        pubkey.setValue("A2CFVQRopFf2FjtWyTxe6ZhDiYgejXcVv1Wx4kzgk32H");

        MsgDeletePublicKey.Value value = new MsgDeletePublicKey.Value();
        value.setDid(did);
        value.setOwnerAddress(owner);
        value.setPubKey(pubkey);

        MsgDeletePublicKey msg = new MsgDeletePublicKey();
        msg.setValue(value);

        broadcastMsgBlock(msg);

        Res<DIDDocument> documentRes = restClient.resolveDID(did);
        System.out.println(documentRes);
    }

    @Test
    public void testMsgAddService() throws PanaceaApiException {
        String did = "did:med:9qWFWcwEPxsk6pVMqU1uoETjmkh38QJGiLhodsjAYcv";
        String owner = "panacea1uekh7wzhmpjwvjz9lul7uakcr004d6rwskzfdf";

        Service service = new Service();
        service.setId("hello");
        service.setType("hub");
        service.setServiceEndPoint("https://www.naver.com/");

        MsgAddService.Value value = new MsgAddService.Value();
        value.setDid(did);
        value.setOwnerAddress(owner);
        value.setService(service);

        MsgAddService msg = new MsgAddService();
        msg.setValue(value);

        broadcastMsgBlock(msg);

        Res<DIDDocument> documentRes = restClient.resolveDID(did);
        System.out.println(documentRes);
    }

    @Test
    public void testMsgDeleteService() throws PanaceaApiException {
        String did = "did:med:HwiHmhWAXpkwbtoePfgGyKUsk2hyzyr5wtrRDa1rwsKg";
        String owner = "panacea1uekh7wzhmpjwvjz9lul7uakcr004d6rwskzfdf";

        MsgDeleteService.Value value = new MsgDeleteService.Value();
        value.setDid(did);
        value.setOwnerAddress(owner);
        value.setServiceId("medi");

        MsgDeleteService msg = new MsgDeleteService();
        msg.setValue(value);

        broadcastMsgBlock(msg);

        Res<DIDDocument> documentRes = restClient.resolveDID(did);
        System.out.println(documentRes);
    }

    @Test
    public void testMsgRevokeDID() throws PanaceaApiException {
        String did = "did:med:2nyj4Egju9DGP6vjgnVr1fH6n9nXq455rY2dWKGGYsrS";

        MsgRevokeDID msg = new MsgRevokeDID();

        MsgRevokeDID.Value value = new MsgRevokeDID.Value();
        value.setDid(did);

        String mnemonic = "clarify clutch decline mirror inform choose letter switch tuna fine blur unknown air record material emotion dust awake deputy bundle provide build shoulder cart";
        Wallet wallet = Wallet.createWalletFromMnemonicCode(mnemonic, "panacea");

        value.setOwnerAddress(wallet.getAddress());

        msg.setValue(value);

        broadcastMsgBlock(msg);

        Res<DIDDocument> documentRes = restClient.resolveDID(did);
        System.out.println(documentRes);
    }

    private TxResponse broadcastMsgBlock(PanaceaTransactionMessage msg) throws PanaceaApiException {
        StdFee fee = new StdFee("umed", "10000", "80000");

        String mnemonic = "clarify clutch decline mirror inform choose letter switch tuna fine blur unknown air record material emotion dust awake deputy bundle provide build shoulder cart";

        Wallet wallet = Wallet.createWalletFromMnemonicCode(mnemonic, "panacea");
        wallet.ensureWalletIsReady(restClient);

        StdTx tx = new StdTx(msg, fee, "");

        try {
            tx.sign(wallet);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        wallet.increaseAccountSequence();

        BroadcastReq req = new BroadcastReq(tx, "block");

        return restClient.broadcast(req);
    }
}
