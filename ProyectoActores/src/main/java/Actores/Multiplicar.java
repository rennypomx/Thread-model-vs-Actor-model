package Actores;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

public class Multiplicar extends UntypedAbstractActor {
    Matriz m1;
    Matriz m2;
    int [][] output;
    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        if (message.getClass() == Matriz.class) {
            if (m1 == null) {
                m1 = (Matriz) message;
            } else {
                m2 = (Matriz) message;
                int tam1 = m1.getValues().length;
                int tam2 = m2.getValues()[0].length;
                if (tam1 == tam2) {
                    output = new int[tam1][tam2];
                    for (var i = 0; i < output.length; i++) {
                        for (var j = 0; j < output[0].length; j++) {
                            ActorSystem actorSystem = ActorSystem.create();
                            ActorRef taskActor = actorSystem.actorOf(Trozo.props(), "Trozo");
                            taskActor.tell(new TaskCalcElement(m1, m2, i, j), self());
                        }
                    }
                }
            }
        } else if (message.getClass() == TaskCalcElement.class){
            TaskCalcElement t = (TaskCalcElement)message;

            output[t.getRowIndex()][t.getCloIndex()] = t.getElement();

            String matriz = new Matriz(output).toString();
            System.out.println(matriz);
        }
    }

    public static Props props(){
        return Props.create(Multiplicar.class);
    }
}
