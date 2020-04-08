package ${packageName}

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.${mainActivityLayout}.*
<#if applicationPackage??>
import ${applicationPackage}.R
</#if>


class ${mainActivityClass}: AppCompatActivity(){

    
    private val viewModel: ${viewModelClass} by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${mainActivityLayout})
        initObservers()
        initViews()
        initListeners()
    }

    private fun initViews() {

    }

    private fun initObservers() {
        viewModel.viewState.observe(this, Observer { state ->
            
        })
    }

    private fun initListeners() {

    }
}
