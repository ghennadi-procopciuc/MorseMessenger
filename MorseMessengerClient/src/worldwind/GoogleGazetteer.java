package worldwind;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.exception.NoItemException;
import gov.nasa.worldwind.exception.ServiceException;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.poi.BasicPointOfInterest;
import gov.nasa.worldwind.poi.Gazetteer;
import gov.nasa.worldwind.poi.POIUtils;
import gov.nasa.worldwind.poi.PointOfInterest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Ghennadi Procopciuc
 */

public class GoogleGazetteer implements Gazetteer {

	private static final String EXTENSION = "xml";
	private static final String GEOCODE_SERVICE = "http://maps.googleapis.com/maps/api/geocode/"
			+ EXTENSION + "?sensor=true&address=";

	public List<PointOfInterest> findPlaces(String lookupString)
			throws NoItemException, ServiceException {

		if (lookupString == null || lookupString.length() < 1)
			return null;

		String urlString;

		try {
			urlString = GEOCODE_SERVICE
					+ URLEncoder.encode(lookupString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			urlString = GEOCODE_SERVICE + lookupString.replaceAll(" ", "+");
		}

		String locationString = POIUtils.callService(urlString);

		if (locationString == null || locationString.length() < 1)
			return null;

		// if (EXTENSION.equals("xml")) {

		// } else {
		// return parseLocationStringJSON(locationString);
		// }
		return parseLocationStringXML(locationString);
	}

	protected ArrayList<PointOfInterest> parseLocationStringXML(
			String locationString) {

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Vector<PointOfInterest> positions = new Vector<PointOfInterest>();

		docBuilderFactory.setNamespaceAware(false);
		DocumentBuilder docBuilder = null;
		Document xmlDoc = null;

		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			xmlDoc = docBuilder.parse(new ByteArrayInputStream(locationString
					.getBytes("UTF-8")));
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(GoogleGazetteer.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (SAXException ex) {
			Logger.getLogger(GoogleGazetteer.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(GoogleGazetteer.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		if (xmlDoc == null) {
			String message = "DocumentIsNull";
			throw new IllegalArgumentException(message);
		}

		String status = xmlDoc.getElementsByTagName("status").item(0)
				.getTextContent();

		// Daca nu s-au obtinut rezultatele asteptate
		if (!status.equals("OK")) {
			return new ArrayList<PointOfInterest>();
		}

		NodeList results = xmlDoc.getElementsByTagName("result");

		for (int i = 0; i < results.getLength(); i++) {
			Node result = results.item(i);
			Node geometry = findChildByName(result, "geometry");
			Node location = findChildByName(geometry, "location");

			String lat = findChildByName(location, "lat").getTextContent();
			String lng = findChildByName(location, "lng").getTextContent();

			String displayName = "";
			NodeList resultChildNodes = result.getChildNodes();

			for (int j = 0; j < resultChildNodes.getLength(); j++) {
				Node child = resultChildNodes.item(j);

				// Componentele adresei
				if (child.getNodeName().equals("address_component")) {

					NodeList address = child.getChildNodes();

					// Obtinere denumire lunga a campului tara/judet/localitate
					for (int k = 0; k < address.getLength(); k++) {

						if (address.item(k).getNodeName().equals("long_name")) {
							displayName += address.item(k).getTextContent();
							if (k != address.getLength() - 1) {
								displayName += ", ";
							}
						}
					}
				}
			}

			// Creare locatie
			LatLon latlon = LatLon.fromDegrees(Double.parseDouble(lat),
					Double.parseDouble(lng));
			PointOfInterest loc = new BasicPointOfInterest(latlon);
			loc.setValue(AVKey.DISPLAY_NAME, displayName);

			positions.add(loc);
		}

		return new ArrayList<PointOfInterest>(positions);
	}

	public static Node findChildByName(Node parent, String localName) {

		NodeList children = parent.getChildNodes();

		if (children == null || children.getLength() < 1)
			return null;

		for (int i = 0; i < children.getLength(); i++) {
			String ln = children.item(i).getNodeName();

			if (ln != null && ln.equals(localName))
				return children.item(i);
		}

		return null;
	}

	public static void main(String[] args) {
		GoogleGazetteer gazetteer = new GoogleGazetteer();

		System.out.println(gazetteer.findPlaces("domnesti"));
	}
}
