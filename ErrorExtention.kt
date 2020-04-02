val Throwable.isNoConnection : Boolean get() = this is UnknownHostException || this is SSLHandshakeException || this is SocketTimeoutException || this is ConnectException
