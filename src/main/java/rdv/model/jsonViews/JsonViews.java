package rdv.model.jsonViews;

public class JsonViews {
	public static class Common {
	};

	public static class PersonneWithLogin extends Common {
	};

	public static class PersonneWithAll extends Common {
	};

	public static class AdresseWithPraticien extends PersonneWithAll {
	};

	public static class LoginWithPersonne extends Common {

	};

	public static class PraticiensWithAdresse extends Common {

	};

	public static class DisponibiliteWithCreneau extends Common {

	};

	public static class DisponibiliteWithPraticien extends Common {

	};

	public static class UneDisponibilite extends Common {

	};

}
