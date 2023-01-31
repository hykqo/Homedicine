const path = require('path');
const webpack = require('webpack');

module.exports = {
    entry: {
        main : [
            '@babel/polyfill',
            './src/main/resources/static/js/main.js',


        ]
    },
    output: {
        filename: "main.js",
        path: path.resolve(__dirname, "src/main/resources/static/dist"),
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                include: path.resolve(__dirname, "src/main/resources/static/js"),
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }

            }
        ]
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery',
        }),
    ],
    devtool: 'source-map',
    // https://webpack.js.org/concepts/mode/#mode-development
    mode: 'development'
};