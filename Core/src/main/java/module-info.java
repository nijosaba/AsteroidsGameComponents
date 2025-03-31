module Core {
    requires javafx.graphics;
    requires javafx.controls;
    requires Common;
    requires spring.core;
    requires spring.context;
    requires spring.beans;

    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    opens dk.sdu.cbse.core to javafx.graphics;
}