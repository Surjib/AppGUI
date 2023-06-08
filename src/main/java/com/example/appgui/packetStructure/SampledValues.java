package com.example.appgui.packetStructure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampledValues {

    private String appId;

    private int length;

    private String reserved1;

    private String reserved2;

    private APDU apdu = new APDU();

}