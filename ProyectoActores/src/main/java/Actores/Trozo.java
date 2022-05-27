package Actores;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

public class Trozo extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        if(message.getClass() == TaskCalcElement.class){
            TaskCalcElement t = (TaskCalcElement) message;
            t.calcValue();
            sender().tell(t, self());
        }
    }

    public static Props props(){
        return Props.create(Trozo.class);
    }
}
