
public enum OptionPartie {

		UN(1, "Continuer"),
		DEUX(2, "Sauvegarder la partie"),
		OUT(0, "Annuler la partie");
		
		private int val;
		private String title;
		
		private OptionPartie(int val, String title) {
			this.val = val;
			this.title = title;
		}
		
		public String toString() {
			String str = new String(val+". "+ title);
			return str;
		}
}
