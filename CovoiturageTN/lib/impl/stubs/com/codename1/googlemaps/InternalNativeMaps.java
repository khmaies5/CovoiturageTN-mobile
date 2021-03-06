package com.codename1.googlemaps;


/**
 *  This is an internal implementation class
 * 
 *  @author Shai Almog
 *  @deprecated used internally please use MapContainer
 */
public interface InternalNativeMaps extends com.codename1.system.NativeInterface {

	public void setMapType(int type);

	public int getMapType();

	public int getMaxZoom();

	public int getMinZoom();

	public long addMarker(byte[] icon, double lat, double lon, String text, String longText, boolean callback);

	public long beginPath();

	public void addToPath(long pathId, double lat, double lon);

	public long finishPath(long pathId);

	public void removeMapElement(long id);

	public void removeAllMarkers();

	public com.codename1.ui.PeerComponent createNativeMap(int mapId);

	public double getLatitude();

	public double getLongitude();

	public void setPosition(double lat, double lon);

	public void setZoom(double lat, double lon, float zoom);

	public float getZoom();

	public void deinitialize();

	public void initialize();

	public void calcScreenPosition(double lat, double lon);

	public int getScreenX();

	public int getScreenY();

	public void calcLatLongPosition(int x, int y);

	public double getScreenLat();

	public double getScreenLon();

	public void setShowMyLocation(boolean show);

	public void setRotateGestureEnabled(boolean e);
}
