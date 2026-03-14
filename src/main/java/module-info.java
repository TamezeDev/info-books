module org.zeki.infobooks {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens org.zeki.infobooks to javafx.fxml;
    exports org.zeki.infobooks;
    exports org.zeki.infobooks.controller;
    opens org.zeki.infobooks.controller to javafx.fxml;
}