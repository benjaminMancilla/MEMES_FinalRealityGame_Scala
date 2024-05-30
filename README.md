# Final Reality

Final Reality is a simplified clone of the renowned game, Final Fantasy. Its main purpose is to
serve as an educational tool, teaching foundational programming concepts.

This README is yours to complete it. Take this opportunity to describe your contributions, the
design decisions you've made, and any other information you deem necessary.

This project is licensed under the
[Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/).

# Tarea 2 | README

## Diseño

Un cambio importante que hice fue el de remover la clase EmptyWeapon que representaba no tener equipado nada, el uso de esto simplificada muchas cosas
pero al momento de tratar excepciones la estructura se volvia poco orientada a objetos, ya que se tenia que explicitamente checkear si el tipo de arma
era un EmptyWeapon, para esto tenia un metodo booleano que tiraba true para empty weapon y false para las demas. Todo esto era legal pero el diseño era pobre,
parecido a lo que pasa en los glitch de algunos juegos antiguos, tal vez existia algun bug que podia hacer que un character pudiera atacar sin arma, ya que 
para romper toda esta logica basta con confundirse en alguno de estos metodos. Ahora que la equipped weapon es un Option[Weapon] no existe forma que esto ocurra.

Para la asignacion hice simplemente un doble dispatch, tambien utilizo overloading, esto es legal, ya que entre las distintas clases concretas de Character no existe
superposicion (son "paralelas"), lo que no hice fue definir los 5 metodos (uno para el checkeo de cada tipo de character) false en la clase abstracta, ya que el overriding si puede confundir, en este caso se necesitarian nombres distintos para que esto no ocurra, y ahi se complica un poco mas la implementacion. Por lo que lo deje asi, es mas "copy paste" pero encuentro que es mejor, si se agrega en el futuro una nueva clase de character hay que activamente implementar el metodo en todas las armas, por lo que el desarrollador tendria que pensar mas de 2 veces si el nuevo personaje sera capaz o no de equiparse cierta arma.

En todo el codigo utilizo excepciones personalizadas mas un mensaje bastante especifico del error, esto sera muy util mas adelante cuando tengamos que programar el controlador. Los nombres de estas son bastante especificos para entender que caso borde tratan, en el caso de haber dudas la documentacion detalla un poco mas.

Lo mas como de haber cambiando el equipped weapon de los Character a un Option[Weapon] es que desequipar es trivial, basta con dejar None el owner de su antigua weapon y que la nueva weapon sea None, antes cada vez que desquipada tenia que crear una nueva emptyweapon, lo cual era algo poco "elegante" para el metodo.

Para evitar ataque entre aliados opte a una estrategia simple aprovechando overloading. Las entities tienen 2 metodos para atacar, una a enemigos y la otra a character, para cada una de estas clases simplemente hago overriding para que el metodo que tenga como objetivo a uno de su misma clase tire excepcion, y la otra que tenga la logica necesaria para un ataque efectivo. Nota importante es que en abstract entity igual existe un metodo doAttack(entity), esto si puede generar confusion, en la practica (y digo en la practica porque hice todos los tests necesarios y funciona bien) nunca se utiliza este metodo general como tal, pero lo deje puesto ya que pueden existir entidades que no entran dentro de estas 2 categorias (por ejemplo un obstaculo, objeto, etc), donde a mi parecer un ataque deberia ser valido a estas entidades extrañas siempre y cuando sean entidades claro.

Para la magia fue tambien agregar mas doubleDispatch, mas que nada para checkear si el objetivo era valido y si el hechizero cumplia con el mismo tipo de la magia. El resto en el cuerpo de los metodos (castSpell especialmente) es bastante directo.

Para el tema de la curacion lo deje tambien general en la clase abstract entity, lo unico que accede a esto es el spell de heal, pero pienso que en un futuro hipotetico se podrian agregar items, acciones, eventos o cualquier otra cosa que puede curar tanto a un character como a un enemy o inclusive otra entidad, por ello decidi dejarlo ahi.

Algo importante es que agregue el metodo getter magicPoints a todas las armas (Osea al trait Weapon), la logica es que se puede tratar de acceder a la magia desde cualquier arma, sin embargo, si esto ocurre conllevara a un error (excepcion), las armas magicas hacen un override a esto, quitando el error y agregando a la funcionalidad del getter como tal, esto funciona facilmente como un dispatch para las cosas magicas que acceden a la equipedWeapon.

Tambien MOMENTANEAMENTE para los metodos applySpell en vez de aplicar el efecto como tal puse un print, ya que igual queria testear si se llegaba a esa linea de codigo (aunque esta no hiciera nada), en el futuro basta con quitar el print y remplazarlo por la implementacion real del efecto.

## Paquetes

Para las excepciones la logica es, si el protagonista de la excepcion es X entonces esta va en el paquete X. Para las excepciones mas generales, las dejamos en el paquete general de exception.

Para la magia deje las cosas generales en magic, y las cosas especificas de un color (white o black) en sus respectivos paquetes. Ser defensivo u ofensivo no es algo exclusivo de un tipo de magia, por lo que se deja en el paquete general.

Lo mismo con las weapon, las comunes (que solo son clases concretas en mi caso, no cree un trait para las armas no magicas) van en su paquete y las magicas tambien.

Esto mismo ocurre con los character.

Quiero mencionar que estas 2 ultimas cosas no las queria hacer, pero se me desconto por tener el codigo organizado asi, asi que me veo obligado a hacerlo D:
En general uso los paquetes para cosas generales, por lo menos la parte magica no me parece tan alejada de la normal, por lo que lo considero una extension, ya en el caso de que sea dificil generalizar ahi creo paquetes mas especificos.