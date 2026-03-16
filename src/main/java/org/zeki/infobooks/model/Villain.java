package org.zeki.infobooks.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Villain implements Serializable {
    
    private final long SERIAL_VERSION_UID = 1L;
    private String name;
}
