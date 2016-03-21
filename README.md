This simple app is to verify ACRA is affecting AsyncTasks's behaviour.
Up to API level 12, AsyncTask can run in parallel. For API level 13+, AsyncTask runs one by one by default.
With ACRA 4.6.2, this is ture. But with ACRA 4.7.2+ (not sure precise boundary) ACRA (or configuration related to ACRA) is affecting
this AysncTask's behaviour.

I wrote simple enough app to verify this issue.

How to test this issue with simple app.
1. Build and run app. You can see 2 lines are updating it's number.
2. Change ACRA's version from 4.6.2 to 4.8.5 and build, run. You see only first line
   is updating it's number.
3. Open MainActivity.java and change
    testTask1.execute();
   to
    testTask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
  this fixes issue but this workaround should not be necessary.

Note that this simple app has no "targetSdkVersion" and "minSdkVersion" is 9.

