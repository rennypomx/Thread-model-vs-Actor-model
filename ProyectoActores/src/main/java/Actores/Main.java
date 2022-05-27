package Actores;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] mat1Values = {
                {1, 2},
                {3, 4},
                {5, 6},
        };
        Matriz m1 = new Matriz(mat1Values);
        int[][] mat2Values = {
                {1, 2, 3},
                {3, 4, 5}
        };
        Matriz m2 = new Matriz(mat2Values);

        ActorSystem actorSystem = ActorSystem.create("system");
        ActorRef taskActor = actorSystem.actorOf(Multiplicar.props(), "Multiplicar");
        taskActor.tell(m1, ActorRef.noSender());
        taskActor.tell(m2, ActorRef.noSender());

        // actorSystem.terminate();
    }


}
