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

    opens org.zeki.infobooks.controller.api to javafx.fxml;
    exports org.zeki.infobooks.controller.scene;
    opens org.zeki.infobooks.controller.scene to javafx.fxml;
    exports org.zeki.infobooks.controller.api;
}