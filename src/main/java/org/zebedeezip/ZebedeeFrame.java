package org.zebedeezip;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import java.util.prefs.BackingStoreException;

/**
 * Got zebedee icon from  http://www.presence-pc.com/forum/ppc/Logiciels/probleme-nero-ralenti-image-sujet-17153-1.htm
 */
public class ZebedeeFrame extends JFrame {

    public ZebedeeFrame() throws HeadlessException {
        super("ZebedeeZip Version 1.0");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initFromPrefs();
        initUI();
        setSize(900, 600);
        centreWindowOnScreen(this);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                if (log.isInfoEnabled()) {
                    log.info("[ShutDown] : closing ZipManager...");
                }
                if (_currentZipContents != null) {
                    try {
                        _currentZipContents.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }));
    }

    private void initUI() {
        JPanel container = new JPanel(new BorderLayout());

        _toolbar = new ZebedeeToolbar(this);
        JPanel toolbarContainer = new JPanel(new BorderLayout());
        toolbarContainer.add(_toolbar, BorderLayout.CENTER);
        JButton zebedee =  new JButton(ActionBase.createImageIcon("icon/zebedee.gif"));
        zebedee.setEnabled(true);
        zebedee.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        toolbarContainer.add(zebedee, BorderLayout.EAST);
        container.add(toolbarContainer, BorderLayout.NORTH);


        _table = new ZipEntryTable(true);


        JScrollPane sp = new JScrollPane(_table);
        sp.setBorder(BorderFactory.createEmptyBorder());
        JPanel spContainer = new JPanel(new BorderLayout());
        spContainer.add(sp, BorderLayout.CENTER);
        spContainer.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));
        container.add(spContainer, BorderLayout.CENTER);

        container.add(Box.createVerticalStrut(15), BorderLayout.SOUTH);
        super.getContentPane().add(container, BorderLayout.CENTER);
    }

    public static void centreWindowOnScreen(Window window) {
        Dimension size = window.getSize();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int x = (int)((screenSize.getWidth() / 2) - (size.getWidth() / 2));
        int y = (int)((screenSize.getHeight() / 2) - (size.getHeight() / 2));

        window.setLocation(x, y);

    }

    public void openZip(File file) throws IOException {
        _startLocation = file.getParentFile().getAbsolutePath();
        syncPrefs();

        if (_currentZipContents != null) {
            _currentZipContents.close();
        }
        _currentZipContents = new ZipFileContents(file);
        _currentZipContents.open();
        _table.setEntries(_currentZipContents.getEntries());
    }

    private void syncPrefs() {
        Preferences prefs = Preferences.userNodeForPackage(ZebedeeFrame.class);
        try {
            prefs.put(PREF_START_LOCATION, _startLocation);
            prefs.sync();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public void initFromPrefs() {
        Preferences prefs = Preferences.userNodeForPackage(ZebedeeFrame.class);

        _startLocation = (String)prefs.get(PREF_START_LOCATION, (new File("")).getAbsolutePath());


    }

    public String getStartLocation() {
        return _startLocation;
    }

    private static final Logger log = Logger.getLogger(ZebedeeFrame.class);
    private ZebedeeToolbar _toolbar;

    public static final String PREF_START_LOCATION = "startLocation";
    private String _startLocation = "~";
    private ZipEntryTable _table;

    private ZipFileContents _currentZipContents;
}
