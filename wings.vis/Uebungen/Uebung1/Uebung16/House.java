public interface House {
	/**
     * @deprecated
     * This method is no longer supported.
     * <p> Use {@link openFrontDoor()} and {@link openBackDoor()}  instead.
     */
	@Deprecated
	void open();
	void openFrontDoor();
	void openBackDoor();
}