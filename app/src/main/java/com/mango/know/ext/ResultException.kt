package com.mango.know.ext

import java.io.IOException

internal class ResultException(var code: String?, var errorMessage: String?) : IOException() {
}
