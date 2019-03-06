require 'wav-file'

wav = open("audio.wav")
format = WavFile::readFormat(wav)
chunk = WavFile::readDataChunk(wav)

wav.close

wavs = chunk.data.unpack('s\*')
lsb = wavs.map{|sample| sample[0]}.join
flag = lsb[(lsb.index('0'))..-1]
puts [flag].pack('b*')