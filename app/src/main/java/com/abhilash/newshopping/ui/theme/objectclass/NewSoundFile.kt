package com.abhilash.newshopping.ui.theme.objectclass

import android.media.AudioTrack
import android.media.AudioFormat
import android.media.AudioAttributes

fun playUltrasonicSound(frequency: Int = 20000, durationMs: Int = 3000) {
    val sampleRate = 44100
    val numSamples = sampleRate * durationMs / 1000
    val samples = ShortArray(numSamples)

    // Generate the sound wave
    for (i in samples.indices) {
        val angle = 2.0 * Math.PI * i / (sampleRate / frequency)
        samples[i] = (Math.sin(angle) * Short.MAX_VALUE).toInt().toShort()
    }

    // Build the AudioTrack
    val audioTrack = AudioTrack.Builder()
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        .setAudioFormat(
            AudioFormat.Builder()
                .setSampleRate(sampleRate)
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .build()
        )
        .setBufferSizeInBytes(samples.size * 2)
        .build()

    // Write the data and play
    audioTrack.write(samples, 0, samples.size)
    audioTrack.play()

    // Stop playback after duration
    Thread.sleep(durationMs.toLong())
    audioTrack.stop()
    audioTrack.release()
}
