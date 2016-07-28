package com.numberniceic.ananya.numberniceic.managers.eventbus;

import com.squareup.otto.Bus;

/**
 * Created by o_ye on 7/22/2016.
 */
public class EventBus {
    private static Bus ourInstance;

    public static Bus getInstance() {
        if (ourInstance == null)
            ourInstance = new Bus();
        return ourInstance;
    }

    private EventBus() {
    }
}
