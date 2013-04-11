package worldwind;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.poi.YahooGazetteer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.netdata.WorldWindData;
import com.netdata.WorldWindResponse;

/**
 * 
 * @author Liviu Ioan
 */
public class WorldWindEx extends JFrame {

	private WorldWindowGLCanvas wwd;

	public WorldWindEx() {

		super("WW");

		System.out.println("Esti o taratura, bre");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		setSize(dim.width * 2 / 3, dim.height * 2 / 3);

		wwd = new WorldWindowGLCanvas();
		wwd.setModel(new BasicModel());

		final JTextField zoom = new JTextField("Locatia dorita ...");

		ArrayList<WorldWindData> pins = new ArrayList<WorldWindData>();
		pins.add(new WorldWindData("Paul", 10, 20, new Integer(3).toString(),
				WorldWindData.PIN));
		pins.add(new WorldWindData("Andrei", 30, 40, new Integer(3).toString(),
				WorldWindData.PIN));
		pins.add(new WorldWindData("Liviu", 50, 60, new Integer(3).toString(),
				WorldWindData.PIN));

		WorldWindResponse data = new WorldWindResponse("Chennadi",
				WorldWindData.PIN, pins);

		PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
		attrs.setImageAddress("images/pushpins/plain-green.png");

		/*
		 * for (WorldWindData wd : data.getList()) { Position addr = new
		 * Position(LatLon.fromDegrees(wd.getLatitude(), wd.getLongitude()), 0);
		 * PointPlacemark marker = new PointPlacemark(addr); RenderableLayer
		 * markerLayer = new RenderableLayer(); marker.setAttributes(attrs);
		 * markerLayer.addRenderable(marker);
		 * 
		 * wwd.getModel().getLayers().add(markerLayer); }
		 */

		Position addr = new Position(LatLon.fromDegrees(45, 45), 0);

		RenderableLayer markerLayer = new RenderableLayer();
		PointPlacemark marker = new PointPlacemark(addr);

		attrs.setImageAddress("images/pushpins/plain-green.png");

		marker.setAttributes(attrs);
		markerLayer.addRenderable(marker);

		wwd.getModel().getLayers().add(markerLayer);

		zoom.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String input = new String(zoom.getText());

				YahooGazetteer zoomTool = new YahooGazetteer();

				try {
					// List<PointOfInterest> results =
					// zoomTool.findPlaces(input);
					// Position addr = new Position(results.get(0).getLatlon(),
					// 0);
					Position addr = new Position(LatLon.fromDegrees(40, 40), 0);

					RenderableLayer markerLayer = new RenderableLayer();
					PointPlacemark marker = new PointPlacemark(addr);
					PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();

					attrs.setImageAddress("images/pushpins/plain-green.png");

					marker.setAttributes(attrs);
					markerLayer.addRenderable(marker);

					System.out.println("esti idiot ...");

					wwd.getModel().getLayers().add(markerLayer);
					wwd.getView().goTo(addr, 25e3);

				} catch (java.lang.IndexOutOfBoundsException error) {
					showDialog();
				}
			}
		});

		add(wwd, BorderLayout.NORTH);
		add(zoom);

		setVisible(true);
	}

	public void showDialog() {
		JOptionPane dialog = new JOptionPane();
		dialog.showMessageDialog(this, "Locatie invalida.", "Eroare",
				JOptionPane.WARNING_MESSAGE);
	}

	public static void main(String[] args) {
		new WorldWindEx();
	}
}
