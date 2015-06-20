package at.fhhagenberg.swe4.campinaAsAService.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import at.fhhagenberg.swe4.campinaAsAService.controller.Controller;
import at.fhhagenberg.swe4.campinaAsAService.helper.Util;
import at.fhhagenberg.swe4.campinaAsAService.models.BaseViewModel;

/**
 * BasicView Class Provides a Basic
 * MasterDetailView To Create a new
 * DetailView for a new Class only needs
 * to extend from this Class with new
 * DataClass
 * 
 * @author Wolfgang
 *
 * @param <T>
 */
public class BasicMasterDetailView<T extends BaseViewModel> {

	protected GridPane pane;
	protected GridPane detailPane;
	protected TableView<T> master;

	protected Button saveButton;
	protected Button addNewButton;
	protected Button deleteButton;

	protected Controller<T> controller;

	/**
	 * Constructor Creates new
	 * MasterDetailView
	 */
	public BasicMasterDetailView() {
		pane = new GridPane();
		pane.setId("default-panel-user-view");

	}

	/**
	 * Fill Basic Table
	 */
	protected void fillTableData() {
		master = Util
				.<T> generateTableView(controller
						.getDataClass());
		master.setItems(controller
				.getDataList());
		pane.setRowIndex(master, 1);
		pane.setColumnIndex(master, 1);
		pane.setHgrow(master,
				Priority.ALWAYS);
		pane.setVgrow(master,
				Priority.ALWAYS);
		pane.getChildren().add(master);
	}

	/**
	 * Generate DetailSection Editable or
	 * not Editable
	 * 
	 * @param editable
	 */
	protected void generateDetailSection(
			boolean editable) {
		detailPane = (Util
				.<T> generateDetailDialog(
						controller.getDataClass(),
						editable));
		pane.setRowIndex(detailPane, 3);
		pane.setColumnIndex(detailPane, 1);
		pane.getChildren().addAll(
				detailPane);
		if (editable) {
			HBox addBox = new HBox();
			addBox.setPadding(new Insets(10,
					10, 10, 10));
			addNewButton = new Button();
			addNewButton.setText("Add new");
			addNewButton
					.setId("add-new-button");
			addBox.getChildren().addAll(
					addNewButton);
			pane.setRowIndex(addBox, 2);
			pane.setColumnIndex(addBox, 1);
			HBox box = new HBox();
			box.setPadding(new Insets(10, 10,
					10, 10));
			saveButton = new Button();
			saveButton.setText("Save");
			saveButton.setId("save-button");

			deleteButton = new Button();
			deleteButton
					.setText("Delete Entry");
			deleteButton
					.setId("delete-button");
			box.getChildren().addAll(
					saveButton, deleteButton);
			pane.setRowIndex(box, 4);
			pane.setColumnIndex(box, 1);
			pane.getChildren().addAll(box,
					addBox);
		}
		Util.writeValuesToDetailPane(
				detailPane,
				controller.getDetailData());
	}

	/**
	 * Register EventHandlers too Basic
	 * Buttons (add-New, save, delete )
	 */
	protected void registerEvents() {
		master
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						new ChangeListener<T>() {
							public void changed(
									ObservableValue<? extends T> observable,
									T oldValue, T newValue) {
								if (newValue != null) {
									controller
											.setDetailData(newValue);
									Util.writeValuesToDetailPane(
											detailPane,
											controller
													.getDetailData());
								}

							}
						});

		if (saveButton != null) {
			saveButton
					.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(
								ActionEvent arg0) {
							Util.getValuesFromDetailPane(
									detailPane,
									controller
											.getDetailData());
							controller.saveDetail();
							refreshTable();
							Util.writeValuesToDetailPane(
									detailPane,
									controller
											.getDetailData());
						}
					});
		}
		if (addNewButton != null) {
			addNewButton
					.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(
								ActionEvent arg0) {
							controller
									.setDetailData(controller
											.newDataInstance());
							Util.writeValuesToDetailPane(
									detailPane,
									controller
											.getDetailData());
						}
					});
		}
		if (deleteButton != null) {
			deleteButton
					.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(
								ActionEvent arg0) {
							controller.deleteDetail();
							refreshTable();
							Util.writeValuesToDetailPane(
									detailPane,
									controller
											.getDetailData());
						}
					});
		}

	}

	/**
	 * private Method to refresh TableView
	 */
	private void refreshTable() {
		master.setItems(null);
		master.layout();
		master.setItems(this.controller
				.getDataList());
	}

	/**
	 * Return GridPane created in
	 * Constructor
	 * 
	 * @return
	 */
	public GridPane getPane() {
		return pane;
	}
}
