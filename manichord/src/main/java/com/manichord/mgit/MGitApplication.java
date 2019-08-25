package com.manichord.mgit;

import me.sheimi.sgit.SGitApplication;
import com.manichord.mgit.transport.MGitHttpConnectionFactory;

public class MGitApplication extends SGitApplication {
    static {
        MGitHttpConnectionFactory.install();
    }
}
