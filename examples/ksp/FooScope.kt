package coffee

@motif.Scope
interface FooScope {

  @motif.Objects
  abstract class Objects {
    fun str(): String {
      return "bar"
    }
  }
}
