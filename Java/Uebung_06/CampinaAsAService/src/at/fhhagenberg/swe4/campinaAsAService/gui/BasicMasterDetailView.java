package at.fhhagenberg.swe4.campinaAsAService.gui;

import at.fhhagenberg.swe4.campinaAsAService.controller.Controller;
import at.fhhagenberg.swe4.campinaAsAService.helper.Util;
import at.fhhagenberg.swe4.campinaAsAService.models.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class BasicMasterDetailView<T> {

	protected GridPane pane;
	protected GridPane detailPane;
	protected TableView<T> master;

	protected Button saveButton;
	protected Button addNewButton;
	protected Button deleteButton;

	protected Controller<T> controller;

	public BasicMasterDetailView() {
		pane = new GridPane();
		pane.setId("default-panel-user-view");

	}

	protected void fillTableData() {
		master = Util.<T> generateTableView(controller.getDataClass());
		master.setItems(controller.getDataList());
		pane.setRowIndex(master, 1);
		pane.setColumnIndex(master, 1);
		pane.setHgrow(master, Priority.ALWAYS);
		pane.setVgrow(master, Priority.ALWAYS);
		pane.getChildren().add(master);
	}

	protected void generateDetailSection(boolean editable) {
		detailPane = (Util.<T> generateDetailDialog(controller.getDataClass(),
				editable));
		pane.setRowIndex(detailPane, 2);
		pane.setColumnIndex(detailPane, 1);
		pane.getChildren().addAll(detailPane);
		if (editable) {
			HBox box = new HBox();
			saveButton = new Button();
			saveButton.setText("Save");
			addNewButton = new Button();
			addNewButton.setText("Add new");
			deleteButton = new Button();
			deleteButton.setText("Delete Entry");
			box.getChildren().addAll(saveButton, addNewButton, deleteButton);
			pane.setRowIndex(box, 3);
			pane.setColumnIndex(box, 1);
			pane.getChildren().add(box);
		}
	}

	protected void registerEvents() {
		master.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<T>() {
					public void changed(
							ObservableValue<? extends T> observable,
							T oldValue, T newValue) {
						if (newValue != null) {
							controller.setDetailData(newValue);
							Util.writeValuesToDetailPane(detailPane,
									controller.getDetailData());
						}

					}
				});
	
		if (saveButton != null) {
			saveButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					Util.getValuesFromDetailPane(detailPane,
							controller.getDetailData());
					controller.saveDetail();
					refreshTable();
					Util.writeValuesToDetailPane(detailPane,
							controller.getDetailData());
				}
			});
		}
		if (addNewButton != null) {
			addNewButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					controller.setDetailData(controller.newDataInstance());
					Util.writeValuesToDetailPane(detailPane,
							controller.getDetailData());
				}
			});
		}
		if (deleteButton != null) {
			deleteButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					controller.deleteDetail();
					refreshTable();
					Util.writeValuesToDetailPane(detailPane,
							controller.getDetailData());
				}
			});
		}

	}

	private void refreshTable() {
		master.setItems(null);
		master.layout();
		master.setItems(this.controller.getDataList());
	}

	public GridPane getPane() {
		return pane;
	}
}
