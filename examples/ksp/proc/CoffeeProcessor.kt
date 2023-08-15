package coffee.proc

import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.gson.Gson

class CoffeeProcessor(private val env: SymbolProcessorEnvironment) : SymbolProcessor {
  override fun process(resolver: Resolver): List<KSAnnotated> {
    val obj: Object = Object()
    val sb: Appendable = StringBuilder("")
    val gson = Gson()
    gson.toJson(obj, sb)
    return emptyList()
  }
}

@AutoService(SymbolProcessorProvider::class)
class CoffeeProcessorProvider : SymbolProcessorProvider {
  override fun create(env: SymbolProcessorEnvironment): SymbolProcessor {
    return CoffeeProcessor(env)
  }
}
