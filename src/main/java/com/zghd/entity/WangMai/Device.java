package com.zghd.entity.WangMai;

/**
 * 设备信息
 */
public class Device {

    /**
     * 设备类型，1=PHONE 2=TABLET
     */
    private int device_type;
    /**
     * 操作系统，1=ANDROID 2=IOS
     */
    private int os_type;
    /**
     * 操作系统版本，至少需要填写主版本号major
     */
    private OsVersion os_version;
    /**
     * 设备厂商名称，中文需要UTF-8 编码
     */
    private String vendor;
    /**
     * 制造商信息
     */
    private String manufacturer;
    /**
     * 设备型号，中文需要UTF-8 编码
     */
    private String model;
    /**
     * 设备屏幕尺寸
     */
    private ScreenSize screen_size;
    /**
     * 设备ppi，表示以像素每英寸表示的屏幕尺寸
     */
    private int ppi;
    /**
     * 设备唯一标识
     */
    private Udid udid;
    /**
     * 设备浏览器信息，广告请求中的ua需使用系统webview的ua
     */
    private String user_agent;
    /**
     * 设备横竖屏，横竖屏状态信息 1=横屏，2=竖屏 默认为竖屏
     */
    private int orientation;

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public int getOs_type() {
        return os_type;
    }

    public void setOs_type(int os_type) {
        this.os_type = os_type;
    }

    public OsVersion getOs_version() {
        return os_version;
    }

    public void setOs_version(OsVersion os_version) {
        this.os_version = os_version;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ScreenSize getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(ScreenSize screen_size) {
        this.screen_size = screen_size;
    }

    public int getPpi() {
        return ppi;
    }

    public void setPpi(int ppi) {
        this.ppi = ppi;
    }

    public Udid getUdid() {
        return udid;
    }

    public void setUdid(Udid udid) {
        this.udid = udid;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
