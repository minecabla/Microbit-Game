import android, time

droid = android.Android()

while 1:
	x = getPackageVersionCode("com.mcabla.microbit.game")
	txt = "app version is: " + str(x)
	droid.makeToast(txt)
	time.sleep(5)
