public @interface SupportAnnotation {
	int id();
	String beschreibung();
	String bearbeiter() default "unbestimmt";
	String datum() default "unbekannt";
}