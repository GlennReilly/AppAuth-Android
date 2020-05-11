package net.openid.appauthdemo

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationManagementActivity
import net.openid.appauth.AuthorizationResponse


class CallbackActivity : AppCompatActivity() {
    companion object {
    const val TAG = "CallbackActivity"
}
    private var mAuthStateManager: AuthStateManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callback)
        mAuthStateManager = AuthStateManager.getInstance(this)

        val data: Uri = intent.data
        Log.i(TAG, data.toString())
        val responseHandlingIntent = AuthorizationManagementActivity.createResponseHandlingIntent(
                this, intent.data)

        val resp = AuthorizationResponse.fromIntent(intent)
        val ex: AuthorizationException? = AuthorizationException.fromIntent(intent)

        Log.i(TAG, resp.toString())
        Log.i(TAG, ex.toString())


        if (resp != null) {
            // authorization completed
            mAuthStateManager?.let { it.updateAfterAuthorization(resp, ex) }
        } else {
            // authorization failed, check ex for more details
        }
    }
}