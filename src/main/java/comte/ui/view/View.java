package comte.ui.view;

/**
 * Application screen view (as in MVC pattern)
 */
interface View {

    /**
     * Display view content
     */
    void display();

    /**
     * Close view and release all resources
     */
    void close();
}
