package dk.sdu.cbse.core;

import javafx.application.Application;

import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);

        for (String bean: ctx.getBeanDefinitionNames()) {
            System.out.println(bean);
        }

        Game game = ctx.getBean(Game.class);
        game.start(stage);
        //game.setupGameLoop();
    }
}