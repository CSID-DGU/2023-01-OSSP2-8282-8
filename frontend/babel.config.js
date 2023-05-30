/*module.exports = function(api) {
  api.cache(true);
  return {
    presets: ['babel-preset-expo'],
  };
};
*/
module.exports = function (api) {
  api.cache(true);

  const presets = ['babel-preset-expo'];
  const plugins = [
    ['@babel/plugin-proposal-class-properties', { loose: true }],
    ['@babel/plugin-proposal-private-methods', { loose: true }],
    ['@babel/plugin-proposal-private-property-in-object', { loose: true }]
  ];

  return {
    presets,
    plugins
  };
};