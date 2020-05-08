 private fun startTimer() {
        startTime = System.currentTimeMillis() + 300000
        presenter.saveTimer(startTime, model?.requestId)
        timerRunnable.run()
    }

    private fun initTimer() {
        timerRunnable = object : Runnable {
            override fun run() {
                val millis = startTime - System.currentTimeMillis()
                if (millis < 0) {
                    timer?.visibilityGone(false)
                    activationButton?.isEnabled = true
                    return
                }
                timer?.visibilityGone(true)
                var seconds = (millis / 1000).toInt()
                val minutes = seconds / 60
                seconds %= 60
                if (minutes == 0) timer?.text = String.format("%1dс", seconds)
                else timer?.text = String.format("%dм %02dс", minutes, seconds)
                timerHandler.postDelayed(this, 500)
            }
        }
    }
