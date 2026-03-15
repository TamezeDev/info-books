module org.zeki.infobooks {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.net.http;
    requires javafx.graphics;
    requires javafx.base;
    requires org.json;

    opens org.zeki.infobooks to javafx.fxml;
    exports org.zeki.infobooks;

    exports org.zeki.infobooks.controller.scene;
    opens org.zeki.infobooks.controller.scene to javafx.fxml;
    exports org.zeki.infobooks.controller.app;
    opens org.zeki.infobooks.controller.app to javafx.fxml;
}